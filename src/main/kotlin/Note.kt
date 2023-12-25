class Note(val name: String, var text: String) {

    override fun toString(): String {
        return "$name: $text"
    }
}