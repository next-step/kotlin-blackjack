package model

class Name(val names: List<String>) {
    init {
        for (name in names) {
            require(name.isNotBlank()) { "$ERROR_MESSAGE 이름이 공백일 수 없습니다." }
            validateNameLength(name)
        }
        validateDuplication()
    }

    constructor(value: String) : this(splitInputValue(value))

    private fun validateNameLength(name: String) {
        require(name.length < 5) { "$ERROR_MESSAGE 이름이 5글자 이상일 수 없습니다." }
    }

    private fun validateDuplication() {
        require(names.size == names.distinct().count()) { "$ERROR_MESSAGE 이름이 중복될 수 없습니다." }
    }

    companion object {
        private const val ERROR_MESSAGE = "[ERROR]"
        private const val NAME_VALUE_DELIMITER = ","

        private fun splitInputValue(inputValue: String): List<String> {
            return inputValue.split(NAME_VALUE_DELIMITER)
        }
    }
}
