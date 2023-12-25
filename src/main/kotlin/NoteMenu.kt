import java.util.Scanner

class NoteMenu(private val archiveMenu: ArchiveMenu, private val notes: MutableList<Note>) {
    fun showNoteMenu() {
        val options = listOf(
            "Создать заметку" to ::createNote,
            "Список заметок" to ::listNotesInArchive,
            "Вернуться к списку архивов" to ::returnToArchiveList
        )

        val menuManager = MenuManager(options)
        menuManager.displayMenu()
    }

    private fun createNote() {
        val scanner = Scanner(System.`in`)

        var noteName: String
        var noteText: String

        while (true) {
            print("Введите имя новой заметки: ")
            noteName = scanner.nextLine().trim()

            if (noteName.isNotBlank()) {
                break
            } else {
                println("Имя заметки не может быть пустым.")
            }
        }

        while (true) {
            print("Введите текст новой заметки: ")
            noteText = scanner.nextLine().trim()

            if (noteText.isNotBlank()) {
                notes.add(Note(noteName, noteText))
                println("Заметка успешно создана.")
                break
            } else {
                println("Текст заметки не может быть пустым.")
            }
        }
    }

    private fun editNoteByIndex(noteIndex: Int) {
        val note = notes[noteIndex - 1]
        println("Выбранная заметка: ${note.name}: ${note.text}")
        println("Введите новый текст для заметки '${note.name}':")
        val newText = readLine()?.trim() ?: ""

        if (newText.isNotBlank()) {
            note.text = newText
            println("Заметка успешно отредактирована.")
        } else {
            println("Текст заметки не может быть пустым. Редактирование отменено.")
        }

    }

    private fun listNotesInArchive() {
        while (true) {
            if (notes.isEmpty()) {
                println("Нет заметок.")
                return
            } else {
                println("Заметки в этом архиве:")
                notes.forEachIndexed { index, note ->
                    println("${index + 1}. ${note.name}: ${note.text}")
                }
            }
            print("Введите номер заметки для редактирования или 0 для выхода: ")
            val choice = readLine()?.toIntOrNull()

            when {
                choice == 0 -> return
                choice != null && choice in 1..notes.size -> {
                    println("Выбранная заметка: ${notes[choice - 1].name}")
                    println("1. Редактировать заметку")
                    println("2. Добавить текст к заметке")
                    println("0. Выйти")

                    print("Выберите действие: ")
                    val action = readLine()?.toIntOrNull()

                    when (action) {
                        1 -> editNoteByIndex(choice)
                        2 -> addTextToNoteByIndex(choice)
                        0 -> return
                        else -> println("Некорректный ввод. Попробуйте еще раз.")
                    }
                }

                else -> println("Некорректный ввод. Попробуйте еще раз.")
            }
        }
    }

    private fun addTextToNoteByIndex(index: Int) {
        val scanner = Scanner(System.`in`)

        println("Текущий текст заметки: ${notes[index - 1].text}")
        print("Введите текст для добавления к заметке '${notes[index - 1].name}': ")
        val additionalText = scanner.nextLine().trim()

        if (additionalText.isNotBlank()) {
            val existingText = notes[index - 1].text
            notes[index - 1].text = "$existingText $additionalText"
            println("Текст успешно добавлен к заметке '${notes[index - 1].name}'.")
        } else {
            println("Текст не может быть пустым.")
        }
    }

    private fun returnToArchiveList() {
        println("Возвращение к списку архивов.")
        archiveMenu.displayMenu()
    }
}
