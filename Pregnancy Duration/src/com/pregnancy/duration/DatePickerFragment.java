package com.pregnancy.duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;


public class DatePickerFragment extends DialogFragment  implements 
		DatePickerDialog.OnDateSetListener {
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		TextView date = (TextView) getActivity().findViewById(R.id.dueDate);
		// Use the current date as the default date in the picker
		//TODO use the current value within the date textview as the default for the datepicker
		DateTime dt = new DateTime(new Date());
		
		Calendar c = new GregorianCalendar(dt.getYear(),dt.getMonthOfYear()-1, dt.getDayOfMonth(),0,0);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
		DatePicker dp = dpd.getDatePicker();
		dp.setMinDate(c.getTimeInMillis());
		return dpd;
	}

	public void onDateSet(DatePicker v, int year, int month, int day) {
		Calendar c = new GregorianCalendar(year, month, day);
		DateTime dt = new DateTime(c.getTimeInMillis());
		Log.v("Due Date Calendar", formatter.format(c.getTime()));
		
		//Able to access the activity using the (MainActivity) getActivity method
		((MainActivity) getActivity()).saveDate(dt);
		
	}
}