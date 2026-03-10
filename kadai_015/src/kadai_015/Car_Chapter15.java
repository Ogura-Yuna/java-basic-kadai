package kadai_015;

public class Car_Chapter15 {
	// フィールド（内部データ）
	private int gear = 1; // 1速から5速のギアを表す
    private int speed = 10;  // ギアチェンジ後の速度を表す

    // コンストラクタ（初期化処理）
    public Car_Chapter15( int gear, int speed ) {
        this.gear  = gear;
        this.speed = speed;
    }

    // 【メソッド】ギアの値により速度を変える
    // afterGear：ギア数
    public void changeGear( int afterGear ) {
    	int beforeGear = this.gear;
    	this.gear = afterGear;
    	System.out.println("ギア" + beforeGear + "からギア" + this.gear + "に切り替えました" );
    }
    
 // 【メソッド】ギアチェンジ後の速度を表示する
    public void run() {
    	switch (gear) {
	    	case 1 -> speed = 10;
	    	case 2 -> speed = 20;
	    	case 3 -> speed = 30;
	    	case 4 -> speed = 40;
	    	case 5 -> speed = 50;
	    	default -> speed = 10;
    	}
        System.out.println("速度は時速" + speed + "kmです" );
    }
}
