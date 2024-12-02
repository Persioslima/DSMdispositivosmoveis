import android.os.Parcel
import android.os.Parcelable

data class Tarefa(
    val nome: String,
    val descricao: String,
    var concluida: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(descricao)
        parcel.writeByte(if (concluida) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Tarefa> {
            override fun createFromParcel(parcel: Parcel): Tarefa {
                return Tarefa(parcel)
            }

            override fun newArray(size: Int): Array<Tarefa?> {
                return arrayOfNulls(size)
            }
        }
    }
}
