package cookies.directive

import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.servlet.http.Cookie

/**
 * Created by lguerin on 10/31/14.
 */
@TestFor(CookiesDirectiveTagLib)
class CookiesDirectiveTagLibSpec extends Specification {

    def "Test cookiesBar tag"() {
        expect:
        // Default message
        tagLib.cookiesBar() ==~ /(?ms)(.*cookies\.directive\.default\.message.*)/

        // Custom message
        applyTemplate('<cd:cookiesBar>Bip bip</cd:cookiesBar>') ==~ /(?ms)(.*Bip bip.*)/

        // With decline button
        tagLib.cookiesBar(displayDecline: 'true') ==~ /(?ms)(.*cookies\.directive\.decline\.label.*)/

        // Custom lifetime
        tagLib.cookiesBar(expires: '15') ==~ /(?ms)(.*expires: 15.*)/

        // Custom domain
        tagLib.cookiesBar(domain: '.mydomain.fr') ==~ /(?ms)(.*domain: "\.mydomain\.fr".*)/

        // Custom path
        tagLib.cookiesBar(path: 'paris') ==~ /(?ms)(.*path: "paris".*)/
    }

    def "Test hasAcceptedCookies tag"() {
        def cookie
        when:
        request.cookies = [] as Cookie[]
        then:
        applyTemplate('<cd:hasAcceptedCookies>Hello</cd:hasAcceptedCookies') == ""

        when:
        cookie = new Cookie("cookies-directive", "")
        request.cookies = [cookie] as Cookie[]
        then:
        applyTemplate('<cd:hasAcceptedCookies>Hello</cd:hasAcceptedCookies') == ""

        when:
        cookie = new Cookie("cookies-directive", CookiesDirectiveTagLib.COOKIE_ACCEPT_VALUE)
        request.cookies = [cookie] as Cookie[]
        then:
        applyTemplate('<cd:hasAcceptedCookies>Hello</cd:hasAcceptedCookies') == "Hello"

        when:
        cookie = new Cookie("cookies-directive", "decline")
        request.cookies = [cookie] as Cookie[]
        then:
        applyTemplate('<cd:hasAcceptedCookies>Hello</cd:hasAcceptedCookies') == ""
    }
}
