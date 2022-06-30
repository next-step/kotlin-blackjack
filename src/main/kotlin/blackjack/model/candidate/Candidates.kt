package blackjack.model.candidate

class Candidates(
    val candidates: List<Candidate>
) {

    val first
        get() = candidates[0]

    init {
        validateNotEmpty(candidates)
    }

    fun findNext(currentCandidate: Candidate): Candidate {
        val nextIndex = candidates.indexOf(currentCandidate) + 1
        val maxIndex = candidates.size - 1

        if (nextIndex > maxIndex) {
            return candidates[nextIndex % candidates.size]
        }

        return candidates[nextIndex]
    }

    private fun validateNotEmpty(candidates: List<Candidate>) =
        require(candidates.isNotEmpty()) { "참가자는 1명 이상이어야 합니다." }
}
