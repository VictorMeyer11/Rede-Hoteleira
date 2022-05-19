package com.example.redehoteleira.screens.stayPeriod

import android.app.DatePickerDialog
import com.example.redehoteleira.domain.models.Hotel


data class StayPeriodUIState(
    val isSelectingFirstDate: Boolean,
    val isSelectingSecondDate: Boolean,
    val shouldButtonActivate: Boolean,
    val firstDateText: String,
    val secondDateText: String,
    val clientType: String,
    val datePicker: DatePickerDialog?,
    val bestHotelAndValue: Pair<Hotel, Int>?
)