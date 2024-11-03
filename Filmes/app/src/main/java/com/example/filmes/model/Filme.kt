package com.example.filmes.model

import android.os.Parcel
import android.os.Parcelable

data class Filme(
    val titulo: String,
    val diretor: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(diretor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Filme> {
        override fun createFromParcel(parcel: Parcel): Filme {
            return Filme(parcel)
        }

        override fun newArray(size: Int): Array<Filme?> {
            return arrayOfNulls(size)
        }
    }
}
