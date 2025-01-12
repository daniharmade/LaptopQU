package com.example.laptopqu.model

import com.example.laptopqu.R

class LaptopData {
    companion object {
        val laptop = listOf(
            Laptop(
                id = "1",
                name = "Asus ROG Strix Scar 15",
                photoUrl = R.drawable.rogstrix, // Change this to R.drawable.rogstrix
                specification = "15.6-inch FHD (1920 x 1080) 300Hz 3ms IPS Display, AMD Ryzen 9 5900HX, NVIDIA GeForce RTX 3080 16GB GDDR6, 32GB DDR4, 1TB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 45.000.000"
            ),
            Laptop(
                id = "2",
                name = "MSI GE76 Raider",
                photoUrl = R.drawable.msiraider,
                specification = "17.3-inch FHD (1920 x 1080) 360Hz 3ms IPS Display, Intel Core i9-11980HK, NVIDIA GeForce RTX 3080 16GB GDDR6, 32GB DDR4, 1TB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 50.000.000"
            ),
            Laptop(
                id = "3",
                name = "Razer Blade 15",
                photoUrl = R.drawable.razerblade,
                specification = "15.6-inch QHD (2560 x 1440) 240Hz 2ms IPS Display, Intel Core i7-10875H, NVIDIA GeForce RTX 3080 16GB GDDR6, 16GB DDR4, 1TB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 40.000.000"
            ),
            Laptop(
                id = "4",
                name = "Alienware m15 R6",
                photoUrl = R.drawable.alienwarem15,
                specification = "15.6-inch FHD (1920 x 1080) 360Hz 1ms IPS Display, Intel Core i7-11800H, NVIDIA GeForce RTX 3070 8GB GDDR6, 16GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 35.000.000"
            ),
            Laptop(
                id = "5",
                name = "Acer Predator Helios 300",
                photoUrl = R.drawable.acerpredator,
                specification = "15.6-inch FHD (1920 x 1080) 240Hz 3ms IPS Display, Intel Core i7-11800H, NVIDIA GeForce RTX 3060 6GB GDDR6, 16GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 25.000.000"
            ),
            Laptop(
                id = "6",
                name = "Lenovo Legion 5 Pro",
                photoUrl = R.drawable.lenovolegion,
                specification = "16-inch QHD (2560 x 1600) 165Hz 3ms IPS Display, AMD Ryzen 7 5800H, NVIDIA GeForce RTX 3060 6GB GDDR6, 16GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 20.000.000"
            ),
            Laptop(
                id = "7",
                name = "HP Omen 15",
                photoUrl = R.drawable.hpomen,
                specification = "15.6-inch FHD (1920 x 1080) 300Hz 3ms IPS Display, Intel Core i7-10750H, NVIDIA GeForce RTX 3060 6GB GDDR6, 16GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 18.000.000"
            ),
            Laptop(
                id = "8",
                name = "Dell G5 15",
                photoUrl = R.drawable.dellg5,
                specification = "15.6-inch FHD (1920 x 1080) 120Hz 3ms IPS Display, Intel Core i5-11400H, NVIDIA GeForce RTX 3050 4GB GDDR6, 8GB DDR4, 256GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 15.000.000"
            ),
            Laptop(
                id = "9",
                name = "Asus TUF Gaming A15",
                photoUrl = R.drawable.asustuf,
                specification = "15.6-inch FHD (1920 x 1080) 144Hz 3ms IPS Display, AMD Ryzen 7 4800H, NVIDIA GeForce RTX 2060 6GB GDDR6, 16GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 12.000.000"
            ),
            Laptop(
                id = "10",
                name = "Acer Nitro 5",
                photoUrl = R.drawable.acernitro5,
                specification = "17.3-inch FHD (1920 x 1080) 144Hz 3ms IPS Display, Intel Core i5-10300H, NVIDIA GeForce GTX 1650 4GB GDDR6, 8GB DDR4, 512GB PCIe NVMe SSD, Windows 10 Home",
                price = "Rp 10.000.000"
            ),
        )
    }
}