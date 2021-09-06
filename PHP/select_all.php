<?php
    // DB 연동을 시작합니다.
    $con=mysqli_connect("localhost", "root", "1232", "myapp");

    // mysqli_connect()에 대한 마지막 호출에 대한 오류 코드 값을 반환한다.
    // echo mysqli_connect() 에서 0은 오류가 발생하지 않았음을 의미한다
    if (mysqli_connect_errno($con)) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    } 

    mysqli_set_charset($con, "utf8");

    $res = mysqli_query($con, "select * from hamster_items");

    $ret = array();

    while($row = mysqli_fetch_array($res)) {
        array_push($ret, 
            array(
            'id' => $row[0], 
            'name' => $row[1], 
            'link' => $row[2],
            'image' => $row[3],
            'lprice' => $row[4],
            'hprice' => $row[5],
            'mall' => $row[6],
            'brand' => $row[7],
            'maker' => $row[8],
            'category1' => $row[9],
            'category2' => $row[10],
            'category3' => $row[11],
            'category4' => $row[12],
            )
        );
    }
echo json_encode(array("result"=>$ret));
mysqli_close($con);
?>