package com.whailetrailhueil.trytoalive.objects.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.helpers.NAMES_OF_SPRITES;
import com.whailetrailhueil.trytoalive.managment.DrawTools;
import com.whailetrailhueil.trytoalive.managment.GameAssetManager;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;

/**
 * Created by User on 15.06.2016.
 */
public class EtherParticle extends AbstractObject {

    private Animation animation;
    private float stateTime;
    private float animatiomFPS = 24;

    TextureRegion textureRegionBuf;

    public EtherParticle(AbstractObject parent, Vector2 center, float sizeX, float rotation) {
        super(parent, center, sizeX, rotation, null);
        //Gdx.app.log("EtherParticle","frame =" + frame);
        setFrame(GameAssetManager.getInstance().get("ether/out/etherpar/etherParticle.atlas", TextureAtlas.class).findRegion("etherParticle2"));
        //Gdx.app.log("EtherParticle","frame =" + frame);
        //generateAnimationFrames();
        stateTime = 0;
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        //textureRegionBuf = animation.getKeyFrame(stateTime,true);
        //Gdx.app.log("EtherParticle","textureRegionBuf = " + textureRegionBuf);
        //setFrame(textureRegionBuf);
        //Gdx.app.log("EtherParticle","frame = " + frame);
        setNextFrame();
        super.update(delta);

    }

    @Override
    public void render(DrawTools drawTools) {
        if(frame != null){
            drawTools.batch.draw(frame, position.x, position.y,size.x/2,size.y/2,size.x,size.y,1,1,rotation);
        }
    }

    public void setNextFrame(){
        if (stateTime > 1/animatiomFPS) {
            int randomInt;
            TextureRegion nextFrame;
            do{
                randomInt = getRandom().nextInt(24);
                nextFrame = GameAssetManager.getInstance().get("ether/out/etherpar/etherParticle.atlas", TextureAtlas.class).findRegion(NAMES_OF_SPRITES.ETHER_PARTICLE + randomInt);
            }while(frame == nextFrame);
            frame = nextFrame;
            rotation = random.nextFloat()*360;
            stateTime = 0;
        }
    }












//    public void generateAnimationFrames(){
//        //массив регионов, нужен для animation
//        Array<TextureRegion> animationFrames = new Array<TextureRegion>();
//        TextureRegion frame;
//        //массив чисел из которых берётся рандом
//        ArrayList<Integer> numbers = new ArrayList<Integer>();
//        int randomInt = 0;
//        for (int i = 0; i <= 23; i++){
//            numbers.add(i);
//            //Gdx.app.log("EtherParticle","numbers[" + i + "] = " + numbers.get(i));
//        }
//        for(int i = 0; i <= 23; i++){
//            randomInt = getRandom().nextInt(numbers.size()); //тут так сделано для того, чтобы взять 24 числа, от 0 до 23 и преобразовать в от 1 до 24
//            //Gdx.app.log("EtherParticle","numbers[" + randomInt + "] = " + numbers.get(randomInt));
//            frame = GameAssetManager.getInstance().get("ether/out/etherpar/etherParticle.atlas", TextureAtlas.class).findRegion(NAMES_OF_SPRITES.ETHER_PARTICLE + numbers.get(randomInt));
//            animationFrames.add(frame);
//            Gdx.app.log("EtherParticle","animationFrames[" + i + "] = " + animationFrames.get(i));
//            numbers.remove(randomInt);
//        }
//
//        animation = new Animation(1/25f,animationFrames);
//        Gdx.app.log("EtherParticle","frameDuration = " + animation.getFrameDuration());
//
//
//    }
}
