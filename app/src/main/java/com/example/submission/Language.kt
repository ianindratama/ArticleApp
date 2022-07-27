package com.example.submission

import java.io.Serializable

data class Language(
    var rank: Int = 0,
    var name: String = "",
    var numOfJobs: String = "",
    var avg_annual_salary: String = "",
    var language_desc: String = "",
    var language_img: Int = 0
): Serializable