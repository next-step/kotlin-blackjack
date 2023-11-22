package blackJack.domain

enum class Answer {
    y,
    n,
    ;

    companion object {
        fun validateAnswer(answer: String) {
            require(values().any { it.name == answer }) { "Answer가 올바르지 않습니다. 'y' 또는 'n'을 입력하세요. 현재 입력: '$answer'" }
        }
    }
}
