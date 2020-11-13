package com.example.dvp.activity_chitiet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dvp.Activity_chititet;
import com.example.dvp.MainActivity;
import com.example.dvp.R;
import com.example.dvp.adapter.listadapter;

import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tablich#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tablich extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tablich() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tablich.
     */
    // TODO: Rename and change types and number of parameters
    public static tablich newInstance(String param1, String param2) {
        tablich fragment = new tablich();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ExpandableListView expandableListView;
    private List<group> groups;
    private Map<group, List<item>> litem;
    private listadapter ladapter;
    TextView ngay;
    Button chonngay;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablich, container, false);
        expandableListView = view.findViewById(R.id.list);
        ngay = view.findViewById(R.id.ngay);
        chonngay = view.findViewById(R.id.chonngay);
        litem = getLitem();
        groups =  new ArrayList<>(litem.keySet());
        ladapter = new listadapter(groups,litem);
        ngay.setText(simpleDateFormat.format(calendar.getTime()));
        expandableListView.setAdapter(ladapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(getContext(),datghe.class);
                startActivity(intent);
                return false;

            }
        });
        chonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();

            }
        });

        return view;
    }
    private Map<group, List<item>> getLitem() {
        Map<group, List<item>> listMap = new HashMap<>();
        String e[] = getResources().getStringArray(R.array.chinhanh);
        String e1[] = getResources().getStringArray(R.array.thoigian);
        for (String c: e
             ) {
            int i=0;
            group a = new group(c,i);
            i++;
            List<item> ob = new ArrayList<>();
            for (int j=0; j < e1.length;j++){
                ob.add(new item(e1[j]));
            }
            listMap.put(a,ob);
        }
        return listMap;
    }
    private void chonNgay(){
        int ng = calendar.get(calendar.DATE);
        int t = calendar.get(calendar.MONTH);
        int n = calendar.get(calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                ngay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },n,t,ng);
     datePickerDialog.show();
    }

}