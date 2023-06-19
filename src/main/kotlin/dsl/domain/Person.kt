package dsl.domain

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages) {
    init {
        require(name.isNotBlank()) { NAME_EMPTY_ERROR_MESSAGE }
        require(company?.isNotBlank() != false) { COMPANY_EMPTY_ERROR_MESSAGE }
    }
    companion object {
        private const val NAME_EMPTY_ERROR_MESSAGE = "이름은 비어있을수 없습니다"
        private const val COMPANY_EMPTY_ERROR_MESSAGE = "회사명은 공백일수 없습니다"
    }
}
