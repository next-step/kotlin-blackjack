package camp.nextstep.edu.step.step1.domain

@JvmInline
value class Company(
    val name: String
) {

    init {
        require(name.isNotBlank()) { "회사 이름이 입력되지 않았습니다." }
    }

    fun getName(): String {
        return name
    }

}
