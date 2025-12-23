package domain.participant

import domain.bet.BetMoney

class Player(val name: String, val betMoney: BetMoney) : Participant()
