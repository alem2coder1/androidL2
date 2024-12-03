package kz.test.lab2.model.entity

import com.google.gson.annotations.SerializedName

data class Info(
    val awards: Any?,
    val born: Any?,
    @SerializedName("cause_of_death")
    val causeOfDeath: Any?,
    val children: Any?,
    val conflicts: Any?,
    val died: Any?,
    @SerializedName("notable_work")
    val notableWork: Any?,
    val occupation: Any?,
    val office: Any?,
    val parents: Any?,
    val partners: Any?,
    @SerializedName("resting_place")
    val restingPlace: Any?,
    val spouses: Any?,
    val years: Any?,

    val burial: Any?,
    val dynasty: Any?,
    val father: Any?,
    val issue: Any?,
    val mother: Any?,
    val spouse: String?
)