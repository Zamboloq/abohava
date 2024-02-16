package yampi.msh.abohava.data.ds.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_city_table")
data class SelectedCity(
    @PrimaryKey
    @ColumnInfo(name = "selected_city")
    val city: String
)
