package blackJack.domain

class Player(name: String?) : People(name) {
    init {
        checkName()
    }

    private fun checkName() {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("이름은 공백값과 null값을 받을수 없습니다.")
        }
    }
}
