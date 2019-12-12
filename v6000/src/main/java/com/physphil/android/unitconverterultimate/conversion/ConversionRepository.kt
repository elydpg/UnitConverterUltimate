package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.data.AreaDataSource
import com.physphil.android.unitconverterultimate.data.DigitalStorageDataSource
import com.physphil.android.unitconverterultimate.data.EnergyDataSource
import com.physphil.android.unitconverterultimate.data.LengthDataSource
import com.physphil.android.unitconverterultimate.data.MassDataSource
import com.physphil.android.unitconverterultimate.data.TemperatureConverter
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.ConversionType
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

class ConversionRepository {

    fun unitsFor(type: ConversionType): List<Unit> =
        when (type) {
            ConversionType.AREA -> Area.all
            ConversionType.CURRENCY -> TODO()
            ConversionType.DIGITAL_STORAGE -> DigitalStorage.all
            ConversionType.ENERGY -> Energy.all
            ConversionType.LENGTH -> Length.all
            ConversionType.MASS -> Mass.all
            ConversionType.TEMPERATURE -> Temperature.all
        }

    fun convert(value: BigDecimal, initial: Unit, final: Unit): BigDecimal =
        when {
            initial == final -> value
            initial is Area && final is Area -> convertArea(value, initial, final)
            initial is DigitalStorage && final is DigitalStorage -> convertDigitalStorage(value, initial, final)
            initial is Energy && final is Energy -> convertEnergy(value, initial, final)
            initial is Length && final is Length -> convertLength(value, initial, final)
            initial is Mass && final is Mass -> convertMass(value, initial, final)
            initial is Temperature && final is Temperature -> TemperatureConverter.convert(value, initial, final)
            else -> throw IllegalArgumentException("The initial unit $initial and final unit $final are not of the same type, and cannot be converted.")
        }

    private fun convertArea(value: BigDecimal, initial: Area, final: Area): BigDecimal =
        convertStandardUnit(value, AreaDataSource.getMultiplier(initial, final))

    private fun convertDigitalStorage(value: BigDecimal, initial: DigitalStorage, final: DigitalStorage): BigDecimal =
        convertStandardUnit(value, DigitalStorageDataSource.getMultiplier(initial, final))

    private fun convertEnergy(value: BigDecimal, initial: Energy, final: Energy): BigDecimal =
        convertStandardUnit(value, EnergyDataSource.getMultiplier(initial, final))

    private fun convertLength(value: BigDecimal, initial: Length, final: Length): BigDecimal =
        convertStandardUnit(value, LengthDataSource.getMultiplier(initial, final))

    private fun convertMass(value: BigDecimal, initial: Mass, final: Mass): BigDecimal =
        convertStandardUnit(value, MassDataSource.getMultiplier(initial, final))

    private fun convertStandardUnit(value: BigDecimal, multiplier: BigDecimal): BigDecimal =
        value * multiplier
}
