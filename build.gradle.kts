plugins {
    java
    `maven-publish`
    alias(libs.plugins.lombok)
    alias(libs.plugins.shadow)
    alias(libs.plugins.plugin.yml)
    alias(libs.plugins.run.paper)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly(libs.folia.api)
    compileOnly(libs.slimefun4)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.guizhan.lib.plugin)
    implementation(libs.bstats.bukkit)
    implementation(libs.jsr305)
}

group = "io.github.thebusybiscuit"
version = "2026.2"

java {
    disableAutoTargetJvm()
    sourceCompatibility = JavaVersion.VERSION_25
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "io.github.thebusybiscuit.extraheads.libs.$last")
    }
    doRelocate("org.bstats")
    doRelocate("javax.annotation")
    minimize()
    archiveClassifier = ""
}

tasks.jar {
    archiveClassifier = "unshaded"
}

bukkit {
    main = "io.github.thebusybiscuit.extraheads.ExtraHeads"
    apiVersion = "1.18"
    authors = listOf("TheBusyBiscuit", "ybw0014")
    description = "A Slimefun Addon that adds heads of mobs"
    website = "https://github.com/SlimefunGuguProject/ExtraHeads"
    depend = listOf("Slimefun")
    softDepend = listOf("PlaceholderAPI", "GuizhanLibPlugin")
    foliaSupported = true
}

runPaper {
    folia {
        registerTask {
            downloadPlugins {
                val t = 114514
                // Slimefun
                url("https://builds.guizhanss.com/api/download/SlimefunGuguProject/Slimefun4/master/latest?t=${t}")
                // GuizhanLibPlugin
                url("https://builds.guizhanss.com/api/download/ybw0014/GuizhanLibPlugin/master/latest?t=${t}")
                // SlimeHUD
                url("https://builds.guizhanss.com/api/download/SlimefunGuguProject/SlimeHUD/master/latest?t=${t}")
                // GuizhanCraft for testing convenient
                url("https://builds.guizhanss.com/api/download/ybw0014/GuizhanCraft/master/latest?t=${t}")
            }
            jvmArgs("-Dcom.mojang.eula.agree=true")
            minecraftVersion("26.2")
        }
    }
}
