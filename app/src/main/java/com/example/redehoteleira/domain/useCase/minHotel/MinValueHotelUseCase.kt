package com.example.redehoteleira.domain.useCase.minHotel

import com.example.redehoteleira.domain.models.Hotel
import com.example.redehoteleira.domain.models.TotalSum
import com.example.redehoteleira.domain.useCase.base.UseCase
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MinValueHotelUseCase(
    private val selectHotelUseCase: SelectHotelUseCase,
    private val calendar: Calendar
): UseCase<MinValueHotelUseCase.Params, Pair<Hotel, Int>>() {
    data class Params(
        val firstDateText: String,
        val secondDateText: String,
        val clientType: String
    )

    override suspend fun execute(params: Params): Pair<Hotel, Int> {
        val (firstDateText, secondDateText, clientType) = params
        val firstDate = LocalDate.parse(firstDateText, DateTimeFormatter
            .ofPattern("dd-MM-yyyy"))
        val secondDate = LocalDate.parse(secondDateText, DateTimeFormatter
            .ofPattern("dd-MM-yyyy"))
        calendar.set(firstDate.year, firstDate.monthValue, firstDate.dayOfMonth)

        var currentDate = LocalDate.of(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        if(clientType == "padrão") {
            while(currentDate != secondDate.plusDays(1)) {
                if(currentDate.dayOfWeek == DayOfWeek.SATURDAY
                    || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    TotalSum.TremBao.total += Hotel.TremBao.weekendDaysPrice.getValue(Hotel.Type.NORMAL)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekendDaysPrice.getValue(Hotel.Type.NORMAL)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekendDaysPrice.getValue(
                        Hotel.Type.NORMAL)
                } else {
                    TotalSum.TremBao.total += Hotel.TremBao.weekdaysPrice.getValue(Hotel.Type.NORMAL)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekdaysPrice.getValue(Hotel.Type.NORMAL)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekdaysPrice.getValue(Hotel.Type.NORMAL)
                }
                currentDate = currentDate.plusDays(1)
            }
        } else if(clientType == "fidelidade") {
            while(currentDate != secondDate.plusDays(1)) {
                if(currentDate.dayOfWeek == DayOfWeek.SATURDAY
                    || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    TotalSum.TremBao.total += Hotel.TremBao.weekendDaysPrice.getValue(Hotel.Type.FIDELITY)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekendDaysPrice.getValue(Hotel.Type.FIDELITY)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekendDaysPrice.getValue(
                        Hotel.Type.FIDELITY)
                } else {
                    TotalSum.TremBao.total += Hotel.TremBao.weekdaysPrice.getValue(Hotel.Type.FIDELITY)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekdaysPrice.getValue(Hotel.Type.FIDELITY)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekdaysPrice.getValue(Hotel.Type.FIDELITY)
                }
                currentDate = currentDate.plusDays(1)
            }
        } else {
            while(currentDate != secondDate.plusDays(1)) {
                if(currentDate.dayOfWeek == DayOfWeek.SATURDAY
                    || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    TotalSum.TremBao.total += Hotel.TremBao.weekendDaysPrice.getValue(Hotel.Type.SPECIAL)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekendDaysPrice.getValue(Hotel.Type.SPECIAL)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekendDaysPrice.getValue(
                        Hotel.Type.SPECIAL)
                } else {
                    TotalSum.TremBao.total += Hotel.TremBao.weekdaysPrice.getValue(Hotel.Type.SPECIAL)
                    TotalSum.UaiSo.total += Hotel.UaiSo.weekdaysPrice.getValue(Hotel.Type.SPECIAL)
                    TotalSum.BaoDemais.total += Hotel.BaoDemais.weekdaysPrice.getValue(Hotel.Type.SPECIAL)
                }
                currentDate = currentDate.plusDays(1)
            }
        }
        return selectHotelUseCase(Unit)
    }
}