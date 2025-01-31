/*
 * Copyright 2018 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.api.models

/**
 * Copyright (c) 2018 Phil Shadlyn
 */
data class Currencies(val currencies: List<Currency>) {
    fun toMap(): Map<Country, Double> {
        val map = mutableMapOf<Country, Double>()
        currencies.forEach {
            try {
                map[Country.valueOf(it.currency)] = it.rate
            } catch (ignored: IllegalArgumentException) {}
        }
        return map
    }
}

data class Currency(val currency: String, val rate: Double)

enum class Country {
    AUD,
    BGN,
    BRL,
    CAD,
    CHF,
    CNY,
    CZK,
    DKK,
    GBP,
    HKD,
    HRK,
    HUF,
    IDR,
    ILS,
    INR,
    ISK,
    JPY,
    KRW,
    MXN,
    MYR,
    NOK,
    NZD,
    PHP,
    PLN,
    RON,
    SEK,
    SGD,
    THB,
    TRY,
    USD,
    ZAR
}