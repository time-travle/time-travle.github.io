<p>
    <a href="#" onclick="refreshSoftWareUse('linux')">返 回</a>

</p>

---

# 虚拟机使用

## 虚拟机

常用的虚拟机VMware ，Virtual

### VMware 工作站：

技术描述

    运行VMware工作站进程的计算机和操作系统被称为宿主机（host）。
    在一个虚拟机中运行的操作系统实例被称为虚拟机客户（guest）。
    类似仿真器，VMware工作站为客户操作系统提供完全虚拟化的硬件集–例如，客户机只会检测到一个AMD PCnet网络适配器，而和宿主机上真正安装的网络适配器的制造和型号无关。
    VMware在虚拟环境中将所有设备虚拟化，包括视频适配器、网络适配器、以及硬盘适配器。
    它还为USB、串行和并行设备提供传递驱动程序（pass-through drivers，指将对这些虚拟设备的访问传递到真实物理设备的驱动程序）。
    
    由于与宿主机的真实硬件无关，所有虚拟机客户使用相同的硬件驱动程序，虚拟机实例是对各种计算机高度可移植的。
    例如，一个运行中的虚拟机可以被暂停下来，并被拷贝到另外一台作为宿主的真实计算机上，然后从其被暂停的确切位置恢复运行。
    借助VMware的VirtualCenter（虚拟机中心）产品中一种称为Vmotion的新功能，甚至可以在移动一个虚拟机时不必将其暂停–就是说现在即使在向不同的宿主机上移植虚拟机时，这些虚拟机仍然可以保持运行。

简单概括：

    VMWare虚拟机软件是一个“虚拟PC”软件，它使你可以在一台机器上同时运行二个或更多Windows、DOS、LINUX系统。
    与“多启动”系统相比，VMWare采用了完全不同的概念。多启动系统在一个时刻只能运行一个系统，在系统切换时需要重新启动机器.
    
    VMWare虚拟机软件来测试软件、测试安装操作系统（如linux）、测试病毒木马等。
    
    VMWare是真正“同时”运行，多个操作系统在主系统的平台上，就象标准Windows应用程序那样切换。
    而且每个操作系统你都可以进行虚拟的分区、配置而不影响真实硬盘的数据，可以通过网卡将几台虚拟机用网卡连接为一个局域网。

相关功能:

    1.不需要分区或重开机就能在同一台PC上使用两种以上的操作系统。
    
    2.完全隔离并且保护不同OS的操作环境以及所有安装在OS上面的应用软件和资料。
    
    3.不同的OS之间还能互动操作，包括网络、周边、文件分享以及复制粘贴功能。
    
    4.有复原（Undo）功能。
    
    5.能够设定并且随时修改操作系统的操作环境，如：内存、磁盘空间、周边设备等等

### Virtual PC：

    如果想做Windows虚拟机的话，Virtual PC是最佳选择了。
    几乎所有找得到的Windows操作系统，都可以在该虚拟机中安装。
    Virtual PC作为MS自已的产品，在自己的平台下使用非常方便，占用内存小，启动也快。
    联网方面，即不用桥接也不用NAT共享虚拟网卡，直接可以作为同一子网的一台普通电脑使用，不用进行任何网络设置即可上网

### VirtualBox：

    Sun公司的产品，属于轻量级的虚拟机平台，而且是开源的，完整安装包很小，不像VMware有几百兆，功能相对也很精简，快照功能这里叫备份和快速修复，在不同的快照间跳转用起来感觉不是很方便，也不能实现文件拖拽的功能。
    
    文件共享方面，叫做“数据空间”，在关机的状态下，先在设置中选择主机的一个目录来加入到固定分配栏中。

    然后在虚拟机中右键单击我的电脑选择“映射网络驱动器”，在文件夹浏览中整个网络里的”VirtualBox Shared Foders”选择刚才共享的那个文件夹，确定后就可以将其映射为我的电脑中的一个盘符使用了

## Docker与VirtualBox的简单区别

	VirtualBox和Docker的区别，一句话总结就是VirtualBox虚拟化硬件，Docker虚拟化操作系统。

	VirtualBox，是创建硬件虚拟化的软件。通常情况下，一个操作系统运行在硬件上，其中硬件和操作系统之间的通信是通过移动数据到内存地址，然后发出指令来通知可使用该数据的硬件（或者是数据在被读取时）。 在VirtualBox（或其它虚拟机）设置的环境中，那些内存地址实际上是虚拟机软件自身的内存区域，并且那些指令是由虚拟机而不是直接由底层的CPU解释的。实际结果是，你在VirtualBox中运行一个操作系统，对于这个操作系统来说，VirtualBox程序看起来像一台完整计算机，硬件以及所有配件都有。实际上它不知道自己是在另一个程序中运行的。

	Docker，则是不进行硬件的虚拟化。相反，它的作用是创建一个文件系统，使其看起来像一个普通的Linux文件系统，并且运行应用程序在一个所有文件和资源都在文件系统内的锁定环境中。事实上，该应用程序的容器并不模仿任何硬件，应用程序仍然在硬件上运行，它只是隔离了应用程序并允许您可以运行该应用程序跟特定的并且完全不是主机操作系统的软件和第三方库合作。这意味着，在启动或停止Docker应用程序时几乎没有开销，因为它们不需要预先分配的内存和磁盘空间等等。因此Docker容器很容易设置或者拆除。此外，容器在假装需要系统中各种硬件组件上运行软件的时候并不浪费任何开销 - 它是直接使用硬件的。

	VirtualBox虚拟化硬件，

## 参考WIKI

- <a href="https://blog.csdn.net/oldboysecurity/article/details/114026670" target="_blank">常用的虚拟机软件有哪些？linux操作系统基础 </a>
- <a href="https://blog.csdn.net/tzhuwb/article/details/77410893" target="_blank">常用虚拟机软件推荐 </a>
- <a href="https://blog.csdn.net/beetleinv/article/details/115940628" target="_blank">虚拟机VirtulBox中的系统
  从一台电脑复制到另一台电脑中 </a>
- <a href="https://blog.csdn.net/qq_39483580/article/details/88804098" target="_blank">vm虚拟机获取ip地址 </a>
- <a href="https://blog.csdn.net/lvxinchun/article/details/106998564" target="_blank">VMware虚拟机下安装CentOS（NAT模式）</a>
- <a href="https://www.cnblogs.com/guang2508/p/13476824.html" target="_blank">VMware虚拟机安装及CentOS系统与配置全过程 </a>
- <a href="https://zhuanlan.zhihu.com/p/145102034" target="_blank">超详细虚拟机VMware安装Centos7教程(图文) </a>
- <a href="https://www.runoob.com/w3cnote/vmware-install-centos7.html" target="_blank">VMware 安装 Centos7 超详细过程 </a>
- <a href="https://www.cnblogs.com/fuzongle/p/12760193.html" target="_blank">最新超详细VMware虚拟机安装完整教程 </a>
