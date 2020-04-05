package org.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.util.FileSystemUtils
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class caseCup


fun main(args: Array<String>) {
    runApplication<caseCup>(*args)

}

@Configuration
class myConfig : WebMvcConfigurer{

    @Autowired
    private val env: Environment? = null

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler(("/**")).addResourceLocations("file:///${env?.getProperty("upload.path")}")

    }

}

//@Bean
//fun embeddedServletContainerFactory(): EmbeddedServletContainerFactory? {
//    return object : TomcatEmbeddedServletContainerFactory("/app", 8080) {
//        protected fun configureContext(
//            context: Context,
//            initializers: Array<ServletContextInitializer?>?
//        ) {
//            context.setDocBase("/path/to/your/docbase")
//            super.configureContext(context, initializers)
//        }
//    }
//}
//
//@Bean
//fun containerCustomizer(): EmbeddedServletContainerCustomizer? {
//    return EmbeddedServletContainerCustomizer { container ->
//        if (container is TomcatEmbeddedServletContainerFactory) {
//            (container as TomcatEmbeddedServletContainerFactory)
//                .addContextCustomizers({ context ->
//                    val docBase: String = context.getDocBase()
//                    FileSystemUtils.deleteRecursively(File(docBase))
//                    val file = File("custom-doc-base")
//                    file.mkdirs()
//                    context.setDocBase(file.getAbsolutePath())
//                })
//        }
//    }
//}