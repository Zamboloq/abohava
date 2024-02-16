package yampi.msh.abohava.data.ds.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long,
    @PrimaryKey
    @ColumnInfo(name = "city")
    val name: String
)
