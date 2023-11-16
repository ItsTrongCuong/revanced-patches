package app.revanced.patches.music.misc.minimizedplayback

import app.revanced.extensions.exception
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.music.misc.minimizedplayback.fingerprints.MinimizedPlaybackManagerFingerprint

@Patch(
    name = "Enable minimized playback",
    description = "Enables minimized playback on Kids music.",
    compatiblePackages = [
        CompatiblePackage(
            "com.google.android.apps.youtube.music",
            [
                "6.15.52",
                "6.20.51",
                "6.26.51",
                "6.27.53"
            ]
        )
    ]
)
@Suppress("unused")
object MinimizedPlaybackPatch : BytecodePatch(
    setOf(MinimizedPlaybackManagerFingerprint)
) {
    override fun execute(context: BytecodeContext) {

        MinimizedPlaybackManagerFingerprint.result?.mutableMethod?.addInstruction(
            0, "return-void"
        ) ?: throw MinimizedPlaybackManagerFingerprint.exception

    }
}
