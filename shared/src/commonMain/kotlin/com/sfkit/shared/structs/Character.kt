@file:Suppress("EXPERIMENTAL_API_USAGE", "EXPERIMENTAL_UNSIGNED_LITERALS")

package com.sfkit.shared.structs

data class Character(
    val id: String,
    val name: String? = null,
    val basicStats: BasicStats = BasicStats()
)

data class BasicStats(
    val vitals: Vitals = Vitals()
)

data class Vitals(
    val hp: UInt = 0u,
    val sp: UInt = 0u,
    val rp: UByte = 0u,
    val attributes: Attributes = Attributes.default()
)

data class Attributes(
    val str: UByte,
    val strMod: Byte,
    val dex: UByte,
    val dexMod: Byte,
    val con: UByte,
    val conMod: Byte,
    val int: UByte,
    val intMod: Byte,
    val wis: UByte,
    val wisMod: Byte,
    val cha: UByte,
    val chaMod: Byte
) {
    constructor(str: UByte, dex: UByte, con: UByte, int: UByte, wis: UByte, cha: UByte) : this(
        str, str.toModifier(),
        dex, dex.toModifier(),
        con, con.toModifier(),
        int, int.toModifier(),
        wis, wis.toModifier(),
        cha, cha.toModifier()
    )

    companion object {
        fun default(): Attributes =
            Attributes(str = 10u, dex = 10u, con = 10u, int = 10u, wis = 10u, cha = 10u)
    }
}

fun UByte.toModifier(): Byte = ((this.toByte() - 10) / 2).toByte()