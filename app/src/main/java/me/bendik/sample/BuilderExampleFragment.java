package me.bendik.sample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;


public class BuilderExampleFragment extends Fragment implements SimpleRangeView.OnRangeLabelsListener, View.OnClickListener {

    LinearLayout rangeViewsContainer;
    EditText editCount;
    EditText editStart;
    EditText editEnd;
    Button btnBuild;
    CheckBox chkFixed;
    EditText editFixedStart;
    EditText editFixedEnd;
    CheckBox chkMovable;
    CheckBox chkLabels;
    CheckBox chkTicks;
    CheckBox chkFixedTicks;
    CheckBox chkActiveTicks;

    public BuilderExampleFragment() {
        // Required empty public constructor
    }

    public static BuilderExampleFragment newInstance() {
        BuilderExampleFragment fragment = new BuilderExampleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_builder_example, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rangeViewsContainer = (LinearLayout) view.findViewById(R.id.range_views_container);
        editCount = (EditText) view.findViewById(R.id.edit_count);
        editStart = (EditText) view.findViewById(R.id.edit_start);
        editEnd = (EditText) view.findViewById(R.id.edit_end);
        chkFixed = (CheckBox) view.findViewById(R.id.chk_fixed);
        editFixedStart = (EditText) view.findViewById(R.id.edit_fixed_start);
        editFixedEnd = (EditText) view.findViewById(R.id.edit_fixed_end);
        chkMovable = (CheckBox) view.findViewById(R.id.chk_movable);
        chkLabels = (CheckBox) view.findViewById(R.id.chk_labels);
        chkTicks = (CheckBox) view.findViewById(R.id.chk_ticks);
        chkFixedTicks = (CheckBox) view.findViewById(R.id.chk_fixed_ticks);
        chkActiveTicks = (CheckBox) view.findViewById(R.id.chk_active_ticks);
        btnBuild = (Button) view.findViewById(R.id.btn_build);

        btnBuild.setOnClickListener(this);

        createRangeView();
    }

    private void createRangeView() {
        rangeViewsContainer.removeAllViews();
        final SimpleRangeView rangeView = new SimpleRangeView.Builder(getContext())
                .count(getInt(editCount.getText().toString(), 10))
                .start(getInt(editStart.getText().toString(), 2))
                .end(getInt(editEnd.getText().toString(), 7))
                .startFixed(getInt(editFixedStart.getText().toString(), 1))
                .endFixed(getInt(editFixedEnd.getText().toString(), 8))
                .showFixedLine(chkFixed.isChecked())
                .movable(chkMovable.isChecked())
                .showLabels(chkLabels.isChecked())
                .showTicks(chkTicks.isChecked())
                .showFixedTicks(chkFixedTicks.isChecked())
                .showActiveTicks(chkActiveTicks.isChecked())
                .onRangeLabelsListener(this)
                .build();
        rangeViewsContainer.addView(rangeView);
    }


    @Nullable @Override
    public String getLabelTextForPosition(@NotNull SimpleRangeView rangeView, int pos, @NotNull SimpleRangeView.State state) {
        return String.valueOf("L" + (pos+1));
    }

    @Override
    public void onClick(View view) {
        createRangeView();
    }

    public int getInt(String s, int defVal) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defVal;
        }
    }
}
