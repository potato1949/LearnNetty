package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer
{
    private final int port;

    public EchoServer(int port)
    {
        this.port = port;
    }

    public void start() throws Exception
    {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
                    .channel(NioServerSocketChannel.class)        //5
                    .localAddress(new InetSocketAddress(port))    //6
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    { //7
                        @Override
                        public void initChannel(SocketChannel ch)
                        {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture f = b.bind().sync();            //8
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9
        } finally
        {
            group.shutdownGracefully().sync();            //10
        }
    }

    public static void main(String[] args) throws Exception
    {
        int port = 58000;        //1
        new EchoServer(port).start();                //2
    }
}
