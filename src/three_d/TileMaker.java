package three_d;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;

class TileMaker
{
  public static final int FLOOR = 0;
  public static final int CEILING = 1;
  public static final int WALL = 2;
  private static final String[] FLOOR_T =
    {
      "gold_block.png", "iron_block.png", "lapis_block.png", "redstone_block.png"
    };
  private static final String[] FLOOR_N =
    {
      "gold_block_n.png", "iron_block_n.png", "lapis_block_n.png", "redstone_block_n.png"
    };
  private static final String[] FLOOR_S =
    {
      "gold_block_s.png", "iron_block_s.png", "lapis_block_s.png", "redstone_block_s.png"
    };
  private static final String[] CEILING_T =
    {
      "gold_block.png", "iron_block.png", "lapis_block.png", "redstone_block.png"
    };
  private static final String[] CEILING_N =
    {
      "gold_block_n.png", "iron_block_n.png", "lapis_block_n.png", "redstone_block_n.png"
    };
  private static final String[] CEILING_S =
    {
      "gold_block_s.png", "iron_block_s.png", "lapis_block_s.png", "redstone_block_s.png"
    };
  private static final String[] WALL_T =
    {
      "brickwork_t.jpg", "crossbeam_t.jpg", "masonry_t.jpg", "wall_t.jpg"
    };
  private static final String[] WALL_S =
    {
      "black_s.jpg", "crossbeam_s.jpg", "black_s.jpg", "wall_s.jpg"
    };
  private static final String[] WALL_N =
    {
      "brickwork_n.jpg", "crossbeam_n.jpg", "masonry_n.jpg", "wall_n.jpg"
    };
  private static final int DIFFUSE = 0;
  private static final int NORMAL = 1;
  private static final int SPECULAR = 2;
  private static final float F0 = (float) 0.0;
  private static final float F2 = (float) 2.0;
  private static final float F8 = (float) 8.0;
  private static final String DIR = "resource/";
  private static final TriangleMesh[] MESH = new TriangleMesh[3];
  private static final PhongMaterial[][] MATERIAL = new PhongMaterial[3][FLOOR_T.length];

  public TileMaker()
  {
    MESH[FLOOR] = floorMesh();
    MESH[CEILING] = ceilingMesh();
    MESH[WALL] = wallMesh();
    Image test = new Image(DIR + FLOOR_T[0]);
    for (int i = 0; i < FLOOR_T.length; i++)
    {
      MATERIAL[FLOOR][i] = new PhongMaterial();
      MATERIAL[FLOOR][i].setBumpMap(new Image(DIR + FLOOR_N[i]));
      MATERIAL[FLOOR][i].setDiffuseMap(new Image(DIR + FLOOR_T[i]));
      MATERIAL[FLOOR][i].setSpecularMap(new Image(DIR + FLOOR_S[i]));
      MATERIAL[CEILING][i] = new PhongMaterial();
      MATERIAL[CEILING][i].setBumpMap(new Image(DIR + CEILING_N[i]));
      MATERIAL[CEILING][i].setDiffuseMap(new Image(DIR + CEILING_T[i]));
      MATERIAL[CEILING][i].setSpecularMap(new Image(DIR + CEILING_S[i]));
      MATERIAL[WALL][i] = new PhongMaterial();
      MATERIAL[WALL][i].setBumpMap(new Image(DIR + WALL_N[i]));
      MATERIAL[WALL][i].setDiffuseMap(new Image(DIR + WALL_T[i]));
      MATERIAL[WALL][i].setSpecularMap(new Image(DIR + WALL_S[i]));
    }
  }

  public static Group tile(int n)
  {
    Group group=new Group();
    Shape3D ceiling = new Box(2, 0, 2);
    ceiling.setTranslateX(1.0);
    ceiling.setTranslateY(-8.0);
    ceiling.setTranslateZ(1.0);
    ceiling.setMaterial(MATERIAL[CEILING][n]);
    Shape3D floor = new Box(2, 0, 2);
    floor.setTranslateX(1.0);
    floor.setTranslateY(0.0);
    floor.setTranslateZ(1.0);
    floor.setMaterial(MATERIAL[FLOOR][n]);
    group.getChildren().addAll(ceiling, floor);
    return group;
  }

  public static Group wall(int n)
  {
    Group group=new Group();
    Shape3D wall = new Box(2, 8, 2);
    wall.setTranslateX(1.0);
    wall.setTranslateY(-4.0);
    wall.setTranslateZ(1.0);
    wall.setMaterial(MATERIAL[WALL][n]);
    group.getChildren().add(wall);
    return group;
  }

  private static TriangleMesh floorMesh()
  {
    TriangleMesh tm = new TriangleMesh();

    tm.getPoints().addAll(
      F0, F0, F0, F2, F0, F0, F2, F0, F2,
      F0, F0, F0, F2, F0, F2, F0, F0, F2
    );
    tm.getTexCoords().addAll(
      F0, F0, F2, F0, F2, F2,
      F0, F0, F2, F2, F0, F2
    );

    return tm;
  }

  private static TriangleMesh ceilingMesh()
  {
    TriangleMesh tm = new TriangleMesh();

    tm.getPoints().addAll(
      F0, F0, F0, F2, F0, F2, F2, F0, F0,
      F0, F0, F0, F0, F0, F2, F2, F0, F2
    );
    tm.getTexCoords().addAll(
      F0, F0, F2, F2, F2, F0,
      F0, F0, F0, F2, F2, F2
    );

    return tm;
  }

  private static TriangleMesh wallMesh()
  {
    TriangleMesh tm = new TriangleMesh();

    tm.getPoints().addAll(
      F0, F0, F0, F2, F8, F0, F2, F0, F0,
      F0, F0, F0, F0, F8, F0, F2, F8, F0
    );
    tm.getTexCoords().addAll(
      F0, F0, F2, F8, F2, F0,
      F0, F0, F0, F8, F2, F8
    );

    return tm;
  }
}
