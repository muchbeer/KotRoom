package raum.muchbeer.kotroom.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import raum.muchbeer.kotroom.db.WordDao
import raum.muchbeer.kotroom.model.Word

class WordRepository(private val wordDao : WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}