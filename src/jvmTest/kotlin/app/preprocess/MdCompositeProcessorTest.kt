package app.preprocess

import kotlin.test.Test
import kotlin.test.assertEquals

class MdCompositeProcessorTest {

    @Test
    fun testProcess() {
        // given
        val testLocation = "sample/Path"
        val testInputMd = """
            {toc.placeholder}
            
            # Main Section
            Sample main text
            
            ## Subsection 1
            Subsection 1 txt
            
            ## Subsection 2
            
            Subsection 2 txt
            
            ### Subsection 2a
            
            Subsection 2a txt
            
            #### Subsection 2b #?$!
            
            Subsection 2b txt
            
            ##### CI/CD [some link](https://google.com)
            
            Text
            
            # Other section
            
            ## Subsection other 2
            
            Subsection other 2 txt
            
            
        """.trimIndent()
        val expectedMd = """
            - [ Main Section](/sample/Path#main-section)
              - [ Subsection 1](/sample/Path#subsection-1)
              - [ Subsection 2](/sample/Path#subsection-2)
                - [ Subsection 2a](/sample/Path#subsection-2a)
                  - [ Subsection 2b #?$!](/sample/Path#subsection-2b-)
                    - [ CI/CD [some link](https://google.com)](/sample/Path#cicd-some-linkhttpsgooglecom)
            - [ Other section](/sample/Path#other-section)
              - [ Subsection other 2](/sample/Path#subsection-other-2)
            
            # Main Section <a id="main-section" href="/sample/Path#main-section" class="anchor-link">🔗</a>
            Sample main text
            
            ## Subsection 1 <a id="subsection-1" href="/sample/Path#subsection-1" class="anchor-link">🔗</a>
            Subsection 1 txt
            
            ## Subsection 2 <a id="subsection-2" href="/sample/Path#subsection-2" class="anchor-link">🔗</a>
            
            Subsection 2 txt
            
            ### Subsection 2a <a id="subsection-2a" href="/sample/Path#subsection-2a" class="anchor-link">🔗</a>
            
            Subsection 2a txt
            
            #### Subsection 2b #?$! <a id="subsection-2b-" href="/sample/Path#subsection-2b-" class="anchor-link">🔗</a>
            
            Subsection 2b txt
            
            ##### CI/CD [some link](https://google.com) <a id="cicd-some-linkhttpsgooglecom" href="/sample/Path#cicd-some-linkhttpsgooglecom" class="anchor-link">🔗</a>
            
            Text
            
            # Other section <a id="other-section" href="/sample/Path#other-section" class="anchor-link">🔗</a>
            
            ## Subsection other 2 <a id="subsection-other-2" href="/sample/Path#subsection-other-2" class="anchor-link">🔗</a>
            
            Subsection other 2 txt
            
            
        """.trimIndent()
        val testInputDto = MdProcessorDto(testInputMd, testLocation)

        // when
        val output = MdCompositeProcessor.process(testInputDto)

        // then
        assertEquals(expectedMd, output)
    }

}
