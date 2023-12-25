import java.util.*
import kotlin.system.exitProcess

class ArchiveMenu {
    private val archives: MutableMap<String, MutableList<Note>> = mutableMapOf()

    fun displayMenu() {
        val options = listOf(
            "Создать архив" to ::createArchive,
            "Это мой уже созданный архив" to ::chooseArchive,
            "Выход" to ::exitMenu
        )

        val menuManager = MenuManager(options)
        menuManager.displayMenu()
    }


    private fun createArchive() {
        val scanner = Scanner(System.`in`)
        var archiveName: String

        while (true) {
            print("Введите название нового архива: ")
            archiveName = scanner.nextLine().trim()

            if (archiveName.isNotBlank()) {
                archives[archiveName] = mutableListOf()
                println("Архив '$archiveName' успешно создан.")
                break
            } else {
                println("Название архива не может быть пустым.")
            }
        }
    }



    private fun chooseArchive() {
        val scanner = Scanner(System.`in`)

        while (true) {
            println("Список архивов:")
            archives.keys.forEachIndexed { index: Int, archive: String ->
                println("${index + 1}. $archive")
            }
            println("Введите номер архива для выбора или введите 0, чтобы вернуться в меню")


            val choice = scanner.nextLine()

            when {
                choice == "0" -> return  // Возвращаемся назад
                choice.toIntOrNull() in 1.. archives.size -> {
                    val selectedArchive = archives.keys.elementAt(choice.toInt() - 1)
                    println("Выбран архив: $selectedArchive")

                    val noteMenu = NoteMenu(this, archives[selectedArchive]!!)
                    noteMenu.showNoteMenu()
                    return
                }
                else -> println("Некорректный выбор архива.")
            }
        }
    }




    private fun exitMenu() {
        println("Выход из меню архивов...")
        println("Программа завершена.")
        exitProcess(0)
    }
}