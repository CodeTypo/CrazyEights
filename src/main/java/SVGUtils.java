import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SVGUtils {
    public static Image clubsSymbol = getSVGSuitSymbol(Suit.CLUBS);
    public static Image diamondsSymbol = getSVGSuitSymbol(Suit.DIAMONDS);
    public static Image spadesSymbol = getSVGSuitSymbol(Suit.SPADES);
    public static Image heartsSymbol = getSVGSuitSymbol(Suit.HEARTS);

    public static Image getSymbol(Suit suit) {
        return switch (suit) {
            case SPADES -> spadesSymbol;
            case CLUBS -> clubsSymbol;
            case HEARTS -> heartsSymbol;
            case DIAMONDS -> diamondsSymbol;
        };
    }

    public static Image getImageFromSVG(String path) {
        Image image = null;

        BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
        transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, 100F);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, 140F);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_BACKGROUND_COLOR, new Color(0.72f, 0.71f, 0.23f));

        return getImage(path, image, transcoder);
    }

    public static String getSVGCardResourcePath(Suit suit, Denomination denomination) {
        return "/imagesSVG/" +
                denomination.getS() +
                suit.getS() +
                ".svg";
    }

    public static Image getSVGSuitSymbol(Suit suit) {
        String path = getSVGCardResourcePath(suit, Denomination.TWO);

        Rectangle aoi = new Rectangle(-37, 62, 75, 75);

        Image image = null;

        BufferedImageTranscoder transcoder = new BufferedImageTranscoder();

        transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, (float) aoi.width);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) aoi.height);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_AOI, aoi);


        return getImage(path, image, transcoder);
    }

    private static Image getImage(String path, Image image, BufferedImageTranscoder transcoder) {
        try (InputStream file = SVGUtils.class.getResourceAsStream(path)) {
            TranscoderInput transIn = new TranscoderInput(file);
            try {
                transcoder.transcode(transIn, null);
                image = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
            } catch (TranscoderException ex) {
                ex.printStackTrace();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return image;
    }
}
