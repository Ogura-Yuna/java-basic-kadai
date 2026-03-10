package kadai_015;

public class CarExec_Chapter15 {

	public static void main(String[] args) {
		// 車データを生成
		Car_Chapter15 car = new Car_Chapter15();

        // 車のギアを3に変更
        car.changeGear( 3 );

        // 走行
        car.run();
	}

}
