package com.example.david.crossfittools;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.david.crossfittools.Settings.ConverterPreference;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConverterFragment extends Fragment implements View.OnClickListener{


    TextView valueTop;
    TextView valueBottom;

    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnZero;
    Button btnDot;
    ImageButton btnBackspace;
    ImageButton convert;

    public ConverterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.converter_kg_lb, container, false);

        defineAllViews(view);
        setListener();

        return view;
    }

    private void defineAllViews(View view){

        valueTop = (TextView) view.findViewById(R.id.valueTop);
        valueBottom = (TextView) view.findViewById(R.id.valueBottom);

        btnOne = (Button) view.findViewById(R.id.btnOne);
        btnTwo = (Button) view.findViewById(R.id.btnTwo);
        btnThree = (Button) view.findViewById(R.id.btnThree);
        btnFour = (Button) view.findViewById(R.id.btnFour);
        btnFive = (Button) view.findViewById(R.id.btnFive);
        btnSix = (Button) view.findViewById(R.id.btnSix);
        btnSeven = (Button) view.findViewById(R.id.btnSeven);
        btnEight = (Button) view.findViewById(R.id.btnEight);
        btnNine = (Button) view.findViewById(R.id.btnNine);
        btnZero = (Button) view.findViewById(R.id.btnZero);
        btnDot = (Button) view.findViewById(R.id.btnDot);

        btnBackspace = (ImageButton) view.findViewById(R.id.btnBackSpace);
        convert = (ImageButton) view.findViewById(R.id.convert);

    }

    private void setListener(){

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
        convert.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnOne:
                setValue("1");
                break;
            case R.id.btnTwo:
                setValue("2");
                break;
            case R.id.btnThree:
                setValue("3");
                break;
            case R.id.btnFour:
                setValue("4");
                break;
            case R.id.btnFive:
                setValue("5");
                break;
            case R.id.btnSix:
                setValue("6");
                break;
            case R.id.btnSeven:
                setValue("7");
                break;
            case R.id.btnEight:
                setValue("8");
                break;
            case R.id.btnNine:
                setValue("9");
                break;
            case R.id.btnZero:
                setValue("0");
                break;
            case R.id.btnBackSpace:
                onBackspace();
                break;
            case R.id.convert:
                setDirectionConvert();
                convertValues();
                break;
        }
    }

    private void convertValues() {

        ConverterPreference preference = new ConverterPreference(this.getActivity());
        boolean directionConvert = preference.getConvertKgToLb();

        String valueStr = valueTop.getText().toString();
        valueStr = valueStr.substring(0, valueStr.length() - 3);

        if (directionConvert) {
            if (valueStr.equals("0")) {

                valueTop.setText("0 kg");
                valueBottom.setText("0 lb");
            }else{
                valueTop.setText(valueStr + " kg");
                valueBottom.setText( "" + Math.round(Long.valueOf(valueStr)/0.454) + " lb");
            }
        } else {
            if (valueStr.equals("0")) {

                valueTop.setText("0 lb");
                valueBottom.setText("0 kg");
            }else{
                valueTop.setText(valueStr + " lb");
                valueBottom.setText( "" + Math.round(Long.valueOf(valueStr)*0.454) + " kg");
            }
        }





    }

    private void setDirectionConvert(){

        ConverterPreference preference = new ConverterPreference(this.getActivity());
        boolean directionConvert = preference.getConvertKgToLb();

        preference.setConvertKgToLb(!directionConvert);

    }

    private void onBackspace(){

        ConverterPreference preference = new ConverterPreference(this.getActivity());
        boolean directionConvert = preference.getConvertKgToLb();

        String contentStr = valueTop.getText().toString();
        contentStr = contentStr.substring(0, contentStr.length() - 3);

        if(directionConvert) {
            if (contentStr.length() == 1) {
                valueTop.setText("0" + " kg");
                valueBottom.setText("0" + " lb");
            } else {
                contentStr = contentStr.substring(0, contentStr.length() - 1);
                valueTop.setText(contentStr + " kg");
                valueBottom.setText("" + Math.round(Long.valueOf(contentStr) / 0.454) + " lb");
            }
        }else{
            if (contentStr.length() == 1) {
                valueTop.setText("0" + " lb");
                valueBottom.setText("0" + " kg");
            } else {
                contentStr = contentStr.substring(0, contentStr.length() - 1);
                valueTop.setText(contentStr + " lb");
                valueBottom.setText("" + Math.round(Long.valueOf(contentStr) * 0.454) + " kg");
            }
        }

    }

    private void setValue(String value){

        ConverterPreference preference = new ConverterPreference(this.getActivity());
        boolean directionConvert = preference.getConvertKgToLb();

        String contentStr = valueTop.getText().toString();

        if(contentStr.length()>9){
            return;
        }
        contentStr = contentStr.substring(0, contentStr.length() - 3);

        if(directionConvert) {
            if (contentStr.equals("0")) {
                valueTop.setText(value + " kg");
                valueBottom.setText("" + Math.round(Long.valueOf(value) / 0.454) + " lb");
            } else {
                long longValueTop = Long.valueOf(contentStr) * 10 + Long.valueOf(value);
                valueTop.setText(String.valueOf(longValueTop) + " kg");

                long longValueBottom = Math.round(longValueTop / 0.454);
                valueBottom.setText(String.valueOf(longValueBottom) + " lb");
            }
        }else{
            if (contentStr.equals("0")) {
                valueTop.setText(value + " lb");
                valueBottom.setText("" + Math.round(Long.valueOf(value) * 0.454) + " kg");
            } else {
                long longValueTop = Long.valueOf(contentStr) * 10 + Long.valueOf(value);
                valueTop.setText(String.valueOf(longValueTop) + " lb");

                long longValueBottom = Math.round(longValueTop * 0.454);
                valueBottom.setText(String.valueOf(longValueBottom) + " kg");
            }
        }

    }
}
