package model

class Names(val values: List<String>) {
    constructor(value: String) : this(splitInputValue(value))

    companion object {
        private const val NAME_VALUE_DELIMITER = ","

        private fun splitInputValue(inputValue: String): List<String> {
            return inputValue.split(NAME_VALUE_DELIMITER)
        }
    }
}
