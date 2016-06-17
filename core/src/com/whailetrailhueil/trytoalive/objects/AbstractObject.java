package com.whailetrailhueil.trytoalive.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.managment.DrawTools;
import com.whailetrailhueil.trytoalive.managment.GameAssetManager;

import java.util.*;

public class AbstractObject {

    protected static Random random = new Random();

    //ссылка на родителя объекта
    protected AbstractObject    parent;
    //список всех объектов в мире, по идее не надо по нему бегать туда сюда и удалять элементы, это приведёт к проблемам
    //private static List<AbstractObject> abstractObjectList = new ArrayList<AbstractObject>();

    //параметры отвечающие за состояние объекта и его положение
    protected Vector2   position; //TODO надо определиться, либо это левый нижний угол, либо это центр, тогда и center не нужен
    protected Vector2   size;
    protected Vector2   center;
    protected float     rotation;

    //цвет, на всякий случай он нужен каждому объекту
    protected Color color;

    //параметры для отрисовки объекта
    protected TextureRegion frame;

    //круг нужен для обработки нажатий
    protected Circle zoneOFTouch;

    //набор конструкторов
    // TODO надо написать конструкторы таким образом, чтобы они инкапсулировались друг в друга, вроде сделано

    public AbstractObject(){
        this(null);
    }

    public AbstractObject(AbstractObject parent){
        this(parent, new Vector2(0,0), 0, 0, null);
    }

    public AbstractObject(AbstractObject parent, Vector2 center, float sizeX, float rotation, String nameOfTexture) {
        this.parent = parent;
        this.center = center;
        this.size = new Vector2(sizeX,sizeX);

        setFrame(GameAssetManager.getInstance().get("cosmos_atlas/cosmos.atlas", TextureAtlas.class).findRegion(nameOfTexture));
//        this.frame = GameAssetManager.getInstance().get("cosmos_atlas/cosmos.atlas", TextureAtlas.class).findRegion(nameOfTexture);
////        if(frame == null){
////            Gdx.app.log("ERROR","AbstractObject frame can't be null");
////        }
//        //если есть текстура в атласе и нормально подргузилась, то надо подогнать под тот размер, который был указан при создании
//        //при этом надо пропорционально увеличить, либо уменьшить относительно заданной длины
//        if(frame != null) {
//            float ratio = this.frame.getRegionHeight()/this.frame.getRegionWidth();
//            size = new Vector2(size.x, size.x * ratio);
//        }

        this.position = new Vector2(center.x-size.x/2,center.y-size.y/2);
        this.rotation = rotation;

        this.zoneOFTouch = new Circle(center.x, center.y, (size.x >= size.y) ? size.x/2 : size.y/2);

//        addToAbstractObjectList();
    }

    public void update(float delta){};

    public void render(DrawTools drawTools){
        if(frame != null) {
            drawTools.batch.draw(frame, position.x, position.y,size.x,size.y);
            //Gdx.app.log("AbstractObject","try to draw frame = " + frame + " " + this);

        }
    };

    //проверка, произошло ли нажатие на объект
    public boolean isPressed(float x, float y){
        return getCircle().contains(x,y);
    }

//    public void addToAbstractObjectList(){
//        abstractObjectList.add(this);
//    }
//
//    public void deleteFromAbstractObjectList(){
//        abstractObjectList.remove(this);
//    }


    //-----------------------------------------------------------------------------------------------------------------------
    //все геттеры
    //


    public static Random getRandom() {
        return random;
    }

    public AbstractObject getParent() {
        return parent;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public float getRotation() {
        return rotation;
    }

    public TextureRegion getFrame() {
        return frame;
    }

    public Circle getCircle() {
        return zoneOFTouch;
    }

//    public static List<AbstractObject> getAbstractObjectList() {
//        return abstractObjectList;
//    }

    public Vector2 getCenter() {
        return center;
    }

    public Color getColor() {
        return color;
    }

    public Circle getZoneOFTouch() {
        return zoneOFTouch;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    //все сеттеры
    //
    public void setParent(AbstractObject parent) {
        this.parent = parent;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setFrame(TextureRegion region) {
        if(region != null) {
            this.frame = region;
            //Gdx.app.log("AbstractObject","region = " + region);
            //Gdx.app.log("AbstractObject","region.width = " + region.getRegionWidth() + " region.height = " + region.getRegionHeight());
            //Gdx.app.log("AbstractObject","ratio1 = " + this.frame.getRegionHeight()/this.frame.getRegionWidth());
            float ratio = (float)this.frame.getRegionHeight()/(float)this.frame.getRegionWidth();
            //Gdx.app.log("AbstractObject","ratio = " + ratio);
            size = new Vector2(size.x, size.x * ratio);
            //Gdx.app.log("AbstractObject","size = " + size);
        }
        else
            this.frame = null;
    }

    public void setCircle(Circle zoneOFTouch) {
        this.zoneOFTouch = zoneOFTouch;
    }

//    public static void setAbstractObjectList(List<AbstractObject> abstractObjectList) {
//        AbstractObject.abstractObjectList = abstractObjectList;
//    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setZoneOFTouch(Circle zoneOFTouch) {
        this.zoneOFTouch = zoneOFTouch;
    }
}
