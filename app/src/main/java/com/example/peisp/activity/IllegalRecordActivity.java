package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.peisp.R;
import com.example.peisp.adapter.TraceListAdapter;
import com.example.peisp.model.Trace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class IllegalRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvBack;
    private ListView mLvTrace;
    private List<Trace> traceList = new ArrayList<>(10);
    private RelativeLayout mBtnDatePicker;
    private TextView mDataPickerTv;
    private TraceListAdapter adapter;

    private TimePickerView pvCustomTime;
    private RelativeLayout mRlGoToIllWorker;


    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 9;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
    private boolean hasAxes = true;
    private boolean hasAxesNames = false;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = true;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;

    private LineChartView chart;
    private LineChartData data;
    public final static String[] xValues = new String[]{"0时", "3时", "6时","9时", "12时", "15时", "18时", "21时", "24时"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_record);


        initView();
        initData();
        initCustomTimePicker();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(IllegalRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mLvTrace = (ListView) findViewById(R.id.lv_trace);
        mBtnDatePicker = (RelativeLayout) findViewById(R.id.btn_date_picker);
        mDataPickerTv = (TextView) findViewById(R.id.data_picker_tv);
        mRlGoToIllWorker = (RelativeLayout) findViewById(R.id.rl_go_to_ill_worker);


        chart = (LineChartView) findViewById(R.id.line_chart_time);
//        chart.setOnValueTouchListener(new ValueTouchListener());

        mBtnDatePicker.setOnClickListener(this);
        mRlGoToIllWorker.setOnClickListener(this);
    }

    private void initData() {
        // 模拟一些假的数据-时间线
        traceList.add(new Trace(new String[]{"1", "2"}, "2020-05-25 17:48:00", ""));
        traceList.add(new Trace(new String[]{"3"}, "2020-05-25 14:13:00", ""));
        adapter = new TraceListAdapter(this, traceList);
        mLvTrace.setAdapter(adapter);

        generateValues();
        generateData();

    }


    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 5, 30);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mDataPickerTv.setText(getTime(date));
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date_picker:
                // pvTime.setDate(Calendar.getInstance());
                /* pvTime.show(); //show timePicker*/
                pvCustomTime.show();//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                break;
            case R.id.rl_go_to_ill_worker:
                Intent intent;
                intent = new Intent(IllegalRecordActivity.this, IllegalRecordWorkerActivity.class);
                startActivity(intent);
                break;
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    /*******
     * 图表数字初始化
     */
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    /*******
     * 图表数据初始化
     */
    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        List<AxisValue> axisValues = new ArrayList<>();//创建x轴数据
        for (int i = 0; i < numberOfLines; ++i) {



            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
                //i仅1次时
                axisValues.add(new AxisValue(i).setLabel(xValues[i]+","+randomNumbersTab[i][j]+"人"));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
//            line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis(axisValues);
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setZoomEnabled(false);
        chart.setValueSelectionEnabled(hasLabelForSelected);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

}
