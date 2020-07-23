package com.example.peisp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;
import com.example.peisp.adapter.AttendanceAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ColumnChartView;

public class AttendanceActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private ListView mLvAttList;

    private AttendanceAdapter attendanceAdapter;

    private ColumnChartView chart;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = false;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = true;
    public final static String[] xValues = new String[]{"周一", "周二", "周三", "周四", "周五", "周六","周日"};
    public final static int[] stateColors = new int[]{
            Color.parseColor("#f65655"),
            Color.parseColor("#faaf15"),
            Color.parseColor("#fff220"),
            Color.parseColor("#00d7d7")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_record);
        initView();
        attendanceAdapter = new AttendanceAdapter(this);
        mLvAttList.setAdapter(attendanceAdapter);

        generateStackedData();
        //prepareDataAnimation();
//        chart.startDataAnimation();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mLvAttList = (ListView) findViewById(R.id.lv_att_list);
        chart = (ColumnChartView) findViewById(R.id.line_chart);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * Generates columns with stacked subcolumns.
     * 堆形柱状图
     */
    private void generateStackedData() {
        int numSubcolumns = 4;
        int numColumns = 7;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axisValues = new ArrayList<>();//创建x轴数据
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 20f + 5, stateColors[j]));
            }

            axisValues.add(new AxisValue(i).setLabel(xValues[i]));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        // Set stacked flag.
        data.setStacked(true);

        if (hasAxes) {
            Axis axisX = new Axis(axisValues);
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            axisX.setLineColor(Color.GRAY);// 设置X轴轴线颜色
            axisY.setLineColor(Color.GRAY);// 设置Y轴轴线颜色
            axisX.setTextColor(Color.BLACK);
            axisY.setTextColor(Color.BLACK);
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setValueSelectionEnabled(hasLabelForSelected);
        chart.setZoomEnabled(false);
        chart.setColumnChartData(data);
    }

    /**
     * To animate values you have to change targets values and then call {@link Chart#startDataAnimation()}
     * method(don't confuse with View.animate()).
     */
    private void prepareDataAnimation() {
        for (Column column : data.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 100);
            }
        }
    }

}
