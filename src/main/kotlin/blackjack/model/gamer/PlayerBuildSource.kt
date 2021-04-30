package blackjack.model.gamer

import blackjack.model.BetMoney

data class PlayerBuildSource(val name: String, val betMoney: BetMoney) {
    constructor(name: String, betMoney: Int) : this(name, BetMoney(betMoney))

    constructor(rawSource: Pair<String, Int>) : this(rawSource.first, rawSource.second)
}
