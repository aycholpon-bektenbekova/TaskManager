package task.manager.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import task.manager.data.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTask(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

}