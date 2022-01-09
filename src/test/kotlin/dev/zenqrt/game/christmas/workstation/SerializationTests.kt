package dev.zenqrt.game.christmas.workstation

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class SerializationTests : ShouldSpec({

    should("serialize workstation") {
        val expected = listOf(WorkstationInteractionBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0), WorkstationInteractionBox(2.0, 2.0, 2.0, 3.0, 3.0, 3.0))
        val actual: List<WorkstationInteractionBox> = Json.decodeFromString(File("./src/test/resources/workstations/test.json").readText(Charsets.UTF_8))

        actual.forEachIndexed { index, workstation ->
            workstation shouldBe expected[index]
        }
    }

})