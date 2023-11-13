package camp.nextstep.edu.step.step1.domain

data class Person(
    val name: String,
    val company: Company,
    val skill: List<Skill>,
    val language: List<Language>
) {
    init {
        require(name.isNotBlank()) { "이름이 입력되지 않았습니다." }
    }

    fun getCompanyName(): String {
        return company.getName()
    }

}
