package blackjack

class ResultToString(private val playerResult: PlayerResult) {
    override fun toString(): String {
        return "${playerResult.wins}승" + "${playerResult.losses}패" + "${playerResult.draws}무"
    }
}
