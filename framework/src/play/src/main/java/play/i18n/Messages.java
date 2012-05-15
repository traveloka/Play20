package play.i18n;

import play.mvc.Http;
import scala.collection.mutable.Buffer;

import java.util.Arrays;

/**
 * High-level internationalisation API.
 */
public class Messages {

    // For injection purpose for testing.  This is a workaround for bypassing the need
    // to query the whole chain of unmockable contextual statis objects during unit tests.
    public static Lang defaultLang = null;

    /**
    * Translates a message.
    *
    * Uses `java.text.MessageFormat` internally to format the message.
    *
    * @param lang the message lang
    * @param key the message key
    * @param args the message arguments
    * @return the formatted message or a default rendering if the key wasn't defined
    */
    public static String get(Lang lang, String key, Object... args) {
        Buffer<Object> scalaArgs = scala.collection.JavaConverters.asScalaBufferConverter(Arrays.asList(args)).asScala();
        return play.api.i18n.Messages.apply(key, scalaArgs, lang);
    }
    
    /**
    * Translates a message.
    *
    * Uses `java.text.MessageFormat` internally to format the message.
    *
    * @param key the message key
    * @param args the message arguments
    * @return the formatted message or a default rendering if the key wasn't defined
    */
    public static String get(String key, Object... args) {
        if (defaultLang == null) {
            Buffer<Object> scalaArgs = scala.collection.JavaConverters.asScalaBufferConverter(Arrays.asList(args)).asScala();
            return play.api.i18n.Messages.apply(key, scalaArgs, play.mvc.Http.Context.Implicit.lang());
        } else {
            return get(defaultLang, key, args);
        }
    }

    /**
     * Translates a message, using a Http.Context not yet bound to the current context.
     * This is useful for example for enabling Messages API for interceptor Action logic.
     * @param ctx
     * @param key
     * @param args
     * @return
     */
    public static String get(Http.Context ctx, String key, Object... args) {
        Lang lang = Lang.preferred(ctx.request().acceptLanguages());
        return get(lang, key, args);
    }
}
