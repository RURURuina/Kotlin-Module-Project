import java.util.Scanner

class MenuManager(private val options: List<Pair<String, () -> Unit>>) {
    fun displayMenu() {
        val scanner = Scanner(System.`in`)

        while (true) {
            println("Выберите пункт:")
            options.forEachIndexed { index, pair ->
                println("$index. ${pair.first}")
            }

            val input = scanner.nextLine()

            when (val choice = input.toIntOrNull()) {
                in 0 until options.size -> options[choice!!].second.invoke()
                else -> println("Некорректный выбор. Попробуйте снова.")
            }
        }
    }
}