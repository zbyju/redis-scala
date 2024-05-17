package codecrafters_redis

import java.net.{InetSocketAddress, ServerSocket}
import scala.util.Using
import java.io.BufferedReader
import java.io.InputStreamReader

object Server {
  def main(args: Array[String]): Unit = {
    val serverSocket = new ServerSocket()
    serverSocket.bind(new InetSocketAddress("localhost", 6379))
    val clientSocket = serverSocket.accept()

    Using(clientSocket.getInputStream()) { inputStream =>
      {
        val reader = new BufferedReader(new InputStreamReader(inputStream))

        Using(clientSocket.getOutputStream()) { outputStream =>
          outputStream.write("+PONG\r\n".getBytes())
        }
      }
    }
    clientSocket.close()
  }
}
