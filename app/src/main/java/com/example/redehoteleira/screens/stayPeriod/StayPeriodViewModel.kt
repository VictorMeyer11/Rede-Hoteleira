package com.example.redehoteleira.screens.stayPeriod

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class StayPeriodViewModel(
    clientType: String
    ): ViewModel() {
    private val _uiState: MutableStateFlow<StayPeriodUIState> = MutableStateFlow(StayPeriodUIState(
        isSelectingFirstDate = false,
        firstDateText = "_ /_ /_",
        isSelectingSecondDate = false,
        secondDateText = "_ /_ /_",
        shouldButtonActivate = false,
        clientType = clientType,
        datePicker = null,
        bestHotelAndValue = null
    ))
    private val listener =  { _: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
        val formattedDate = "${DecimalFormat("00").format(dayOfMonth)}-${
            DecimalFormat("00").format(selectedMonth + 1)
        }-$selectedYear"

        _uiState.update {
            if(it.isSelectingFirstDate) {
                it.copy(
                    firstDateText = formattedDate,
                    isSelectingFirstDate = false
                )
            } else {
                it.copy(
                    secondDateText = formattedDate,
                    isSelectingSecondDate = false
                )
            }
        }
        switchDates()
        checkIfButtonShouldActivate()
    }
    val uiState: StateFlow<StayPeriodUIState> = _uiState

    private fun switchDates() {
        if(uiState.value.firstDateText != "_ /_ /_" && uiState.value.secondDateText != "_ /_ /_") {
            val firstDate = LocalDate.parse(
                uiState.value.firstDateText, DateTimeFormatter
                    .ofPattern("dd-MM-yyyy")
            )
            val secondDate = LocalDate.parse(
                uiState.value.secondDateText, DateTimeFormatter
                    .ofPattern("dd-MM-yyyy")
            )

            if (firstDate > secondDate) {
                _uiState.update {
                    it.copy(
                        firstDateText = it.secondDateText,
                        secondDateText = it.firstDateText
                    )
                }
            }
        }
    }

    private fun checkIfButtonShouldActivate() {
        _uiState.update {
            if(it.firstDateText != "_ /_ /_" && it.secondDateText != "_ /_ /_") {
                it.copy(
                    shouldButtonActivate = true
                )
            } else {
                it.copy(
                    shouldButtonActivate = false
                )
            }
        }
    }

    fun getDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(
            context, listener, year, month, day
        )
        datePicker.datePicker.minDate = System.currentTimeMillis()

        _uiState.update {
            it.copy(
                datePicker =  datePicker
            )
        }
    }

    fun firstButtonClicked() {
        _uiState.update {
            it.copy(
                isSelectingFirstDate = true,
                isSelectingSecondDate = false
            )
        }
    }
    fun secondButtonClicked() {
        _uiState.update {
            it.copy(
                isSelectingSecondDate = true,
                isSelectingFirstDate = false
            )
        }
    }
}