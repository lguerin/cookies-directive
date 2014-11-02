package cookies.directive

/**
 * Cookies Directive taglib.
 *
 * @author lguerin
 */
class CookiesDirectiveTagLib {

    static namespace = "cd"

    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [cookiesBar: [taglib:'none']]

    static final COOKIE_NAME = "cookies-directive"
    static final COOKIE_ACCEPT_VALUE = "accepted"
    static final COOKIES_DEFAULT_MAX_DAYS = 30

    /**
     * Tag displaying a bar that lets the user accept or decline usage of cookies,
     * according to the EU cookie law (e-Privacy Directive).
     *
     * @attr    expires         Lifetime of the cookie, in days. Default: 30 days
     * @attr    domain          The domain where the cookie is valid. Default: domain of page where the cookie was created.
     * @attr    path            Path where the cookie is valid. Default: '/'
     * @attr    secure          If true, the cookie transmission requires HTTPS. Default: false
     * @attr    bottom          If true, force the bar to be sticked at the bottom of the page. Default: false
     * @attr    displayDecline  If true, displays the decline button. Default: false
     */
    def cookiesBar = {attrs, body ->
        def displayDecline = false
        if (attrs.displayDecline instanceof String) displayDecline = Boolean.valueOf(attrs.displayDecline)

        def expires = attrs.expires?.isInteger() ? attrs.expires?.toInteger() : COOKIES_DEFAULT_MAX_DAYS
        def domain = attrs.domain ?: ""
        def path = attrs.path ?: "/"

        def secure = false
        if (attrs.secure instanceof String) secure = Boolean.valueOf(attrs.secure)

        def bottom = false
        if (attrs.bottom instanceof String) bottom = Boolean.valueOf(attrs.bottom)
        def cssBottom = bottom ? "bottom" : ""

        // Rendering
        out << render(template: "/tpl/cookiesBar", plugin: "cookies-directive",
                model: [body: body(),
                        expires: expires,
                        domain: domain,
                        path: path,
                        displayDecline: displayDecline,
                        secure: secure,
                        cssBottom: cssBottom])
    }

    /**
     * Check if the user has given his consent for the usage of cookies.
     */
    def hasAcceptedCookies = { attrs, body ->
        def hasAccept = false;
        def value = request.cookies.find { it.name == COOKIE_NAME }?.value
        if (value && value == COOKIE_ACCEPT_VALUE) hasAccept = true

        def writer = out
        if (hasAccept) {
            writer << body()
        }
    }
}
