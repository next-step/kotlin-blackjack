package blackJack.domain

enum class DealerResult(private var count: Int) {
    WIN(0),
    DRAW(0),
    LOSE(0);

    fun addCount() {
        count += 1
    }

    fun getCount(): Int = count

    companion object {
        private const val DEFAULT = 0

        fun resetResult() {
            values().forEach { it.count = DEFAULT }
        }
    }
}
