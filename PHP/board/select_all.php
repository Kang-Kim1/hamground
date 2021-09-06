<?php
    // DB 연동을 시작합니다.
    $con=mysqli_connect("localhost", "root", "1232", "myapp");

    // mysqli_connect()에 대한 마지막 호출에 대한 오류 코드 값을 반환한다.
    // echo mysqli_connect() 에서 0은 오류가 발생하지 않았음을 의미한다
    if (mysqli_connect_errno($con)) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    } 

    mysqli_set_charset($con, "utf8");

    $res = mysqli_query($con, "select * from posting");

    $ret = array();

    while($row = mysqli_fetch_array($res)) {
        array_push($ret, 
            array(
            'p_id' => $row[0], 
            'p_title' => $row[1], 
            'p_content' => $row[2],
            'p_writer' => $row[3],
            'p_time' => $row[4],
            'p_likes' => $row[5],
            'p_count' => $row[6],
            'p_category' => $row[7],
            )
        );
    }
echo json_encode(array("result"=>$ret));
mysqli_close($con);
?>